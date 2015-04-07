package com.sdl.selenium.web.form;

import com.sdl.selenium.web.By;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.Utils;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextField extends WebLocator implements ITextField {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextField.class);

    private String type;

    public TextField(By ...bys) {
        getPathBuilder().setTemplate("input-type", "@type='%s'");
        getPathBuilder().init(bys);
        getPathBuilder().defaults(By.tag("input"));
    }

    public TextField(WebLocator container) {
        this();
        getPathBuilder().setContainer(container);
    }

    public TextField(String id) {
        this(By.id(id));
    }

    public String getType() {
        return type;
    }

    public <T extends WebLocatorAbstractBuilder> T setType(String type) {
        this.type = type;
        setTemplateValue("input-type", type);
        return (T) this;
    }

    public boolean pasteInValue(String value) {
        if (ready()) {
            if (value != null) {
                currentElement.clear();
                Utils.copyToClipboard(value);
                currentElement.sendKeys(Keys.CONTROL, "v");
                LOGGER.info("Set value(" + this + "): " + value + "'");
                return true;
            }
        } else {
            LOGGER.warn("setValue : field is not ready for use: " + toString());
        }
        return false;
    }

    public boolean setValue(String value) {
        return executor.setValue(this, value);
    }

    public String getValue() {
        return executor.getValue(this);
    }
}