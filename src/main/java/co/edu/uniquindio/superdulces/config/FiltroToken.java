package co.edu.uniquindio.superdulces.config;

import co.edu.uniquindio.superdulces.dto.configDTO.MessageDTO;
import co.edu.uniquindio.superdulces.model.enums.Rol;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@AllArgsConstructor
public class FiltroToken extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Configuración de cabeceras para CORS
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, Content-Type, Authorization");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            //Obtener la URI de la petición que se está realizando
            String requestURI = request.getRequestURI();
            //Se obtiene el token de la petición del encabezado del mensaje HTTP
            String token = getToken(request);
            boolean error = true;
            try {

                if (requestURI.startsWith("/api/product")) {
                    error = validarToken(token, Rol.ADMIN);
                } else if (requestURI.startsWith("/api/supplier")) {
                    error = validarToken(token, Rol.ADMIN);
                } else if (requestURI.startsWith("/api/worker")) {
                    validarToken(token, Rol.ADMIN);
                } else {
                    error = false;
                }

                //Si hay un error se crea una respuesta con el mensaje del error
                if (error) {
                    crearRespuestaError("Dont have enough privileges to use this resource ", HttpServletResponse.SC_FORBIDDEN, response);
                }


            } catch (MalformedJwtException e) {
                crearRespuestaError("Token incorrect", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);
            } catch (ExpiredJwtException e) {
                crearRespuestaError("Token expired", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);
            } catch (Exception e) {
                crearRespuestaError(e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);
            }
            //Si no hay errores se continúa con la petición
            if (!error) {
                filterChain.doFilter(request, response);
            }

        }
    }


    private void crearRespuestaError(String mensaje, int codigoError, HttpServletResponse response) throws IOException {
        MessageDTO<String> dto = new MessageDTO<>(true, mensaje);


        response.setContentType("application/json");
        response.setStatus(codigoError);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();
    }

    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        return header != null && header.startsWith("Bearer ") ? header.replace("Bearer ", "") : null;
    }

    private boolean validarToken(String token, Rol rol) {
        boolean error = true;
        if (token != null) {
            Jws<Claims> jws = jwtUtils.parseJwt(token);
            if (Rol.valueOf(jws.getPayload().get("rol").toString()) == rol) {
                error = false;
            }
        }
        return error;
    }
}

