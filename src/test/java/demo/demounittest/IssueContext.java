package demo.demounittest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "demo.demounittest",
    "demo.demounittest"
})
public class IssueContext {

}
