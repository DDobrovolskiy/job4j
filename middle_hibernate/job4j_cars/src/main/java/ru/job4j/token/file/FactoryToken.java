package ru.job4j.token.file;

import net.jcip.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Part;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ThreadSafe
public class FactoryToken {
    private static final Logger LOG = LoggerFactory.getLogger(FactoryToken.class.getName());
    private static final Pattern PERMISSION = Pattern.compile("(\\w+).(\\w+)");

    public static synchronized Token produce(Part part) {
        String type = null;
        Matcher matcher = PERMISSION.matcher(part.getSubmittedFileName());
        if (matcher.find()) {
            type = "." + matcher.group(2);
        }
        LOG.debug("Token name: {}", part.getName());
        LOG.debug("Token filename: {}", part.getSubmittedFileName());
        LOG.debug("Token type: {}", type);
        return new TokenDefault(part.getName(), part.getSubmittedFileName(), type);
    }
}
