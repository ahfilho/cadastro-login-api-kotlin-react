package br.com.api.kotlin.auth.token

//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm
import io.jsonwebtoken.Claims
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtToken {

    @Value("\${jwt.auth.app}")
    val appName: String? = null

    @Value("\${jwt.auth.secret_key}")
    val secretKey: String? = null

    @Value("\${jwt.auth.expires_in}")
    val expiresIn: Int? = null

    private val SIGNATURE_ALGORITHM = io.jsonwebtoken.SignatureAlgorithm.HS256

    private fun getAllClaimsFromToken(token: String): Claims? {
        var claims: Claims? = try {
            Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
        } catch (e: Exception) {
            null
        }
        return claims
    }

    fun getUsernameFromToken(token: String?): String? {
        var username: String?
        try {
            val claims: Claims? = token?.let { this.getAllClaimsFromToken(it) }
            username = claims!!.subject
        } catch (e: Exception) {
            username = null
        }
        return username
    }


    @Throws(InvalidKeySpecException::class, NoSuchAlgorithmException::class)
    fun generateToken(username: String?): String {
        return Jwts.builder()
            .setIssuer(appName)
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(generateExpirationDate())
            .signWith(SIGNATURE_ALGORITHM, secretKey)
            .compact()
    }

    private fun generateExpirationDate(): Date {
        return Date(Date().time + expiresIn!! * 1000)
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        var username: String = getUsernameFromToken(token).toString()
        return (username != null && username == userDetails.username && !isTokenExpired(token)
                )
    }

    fun isTokenExpired(token: String?): Boolean {
        val expireDate: Date = getExpirationDate(token.toString())
        return expireDate.before(Date())
    }

    private fun getExpirationDate(token: String): Date {
        var expirationDate: Date
        try {
            var claims: Claims? = this.getAllClaimsFromToken(token)
            expirationDate = claims!!.expiration
        } catch (e: Exception) {
            expirationDate = null!!
        }
        return expirationDate
    }

    fun getIssueAtDateFromToken(token: String): Date? {
        var issueAt: Date?
        try {
            var claims: Claims = this.getAllClaimsFromToken(token)!!
            issueAt = claims.issuedAt
        } catch (e: Exception) {
            issueAt = null
        }
        return issueAt
    }

    fun getToken(request: HttpServletRequest): String? {
        val authHeader: String = getAuthHeaderFromHeader(request)
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7)
        }

        return null
    }
    fun getAuthHeaderFromHeader(request: HttpServletRequest): String {
        return request.getHeader("Authorization")
    }
}