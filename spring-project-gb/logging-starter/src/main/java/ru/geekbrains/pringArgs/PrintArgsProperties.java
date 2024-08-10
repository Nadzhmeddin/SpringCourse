package ru.geekbrains.pringArgs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application.print-args")
public class PrintArgsProperties {

    private boolean printArgs = false;
}
