package be.bruxellesformation.bf_projet_final.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix= "jwt")
@EqualsAndHashCode
@ToString
public class JwtProperties {
    @Getter
    @Setter
    private String secret;
    @Getter @Setter
    private int expiration;
}
