package wang.diff.scaffold.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(type = SecuritySchemeType.HTTP, name = "bearerAuth", scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class Swagger3Config {
}
