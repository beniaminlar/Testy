package com.sdl.selenium.extjs6.button;

import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplitButton extends Button {
    private static final Logger LOGGER = LoggerFactory.getLogger(SplitButton.class);

    public SplitButton() {
        withClassName("SplitButton");
    }

    public SplitButton(WebLocator container) {
        this();
        withContainer(container);
    }

    public SplitButton(WebLocator container, String text) {
        this(container);
        withText(text);
    }

    public boolean clickOnMenu(String option) {
        return clickOnMenu(new String[]{option});
    }

    public boolean clickOnMenu(String[] menuOptions) {
        int n = menuOptions.length;
        LOGGER.debug("clickOnMenu : " + menuOptions[n - 1]);
        assertReady();
        boolean selected = true;
        if (showMenu()) {
            for (String val : menuOptions) {
                WebLocator option = getComboEl(val, false, 300);
                selected = selected && option.click();
            }
        } else {
            LOGGER.debug("(" + toString() + ") The element arrow could not be located.");
            selected = false;
        }
        return selected;
    }

    private WebLocator getComboEl(String value, boolean startWith, long optionRenderMillis) {
        WebLocator comboListElement = new WebLocator("x-menu").withAttribute("aria-hidden", "false").withInfoMessage(this + " -> x-menu");
        return new WebLocator(comboListElement).withText(value, startWith ? SearchType.STARTS_WITH : SearchType.EQUALS).withRenderMillis(optionRenderMillis).withInfoMessage(value);
    }
}
