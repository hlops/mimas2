package com.hlops.mimas.model.leftMenu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 25.06.13
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
public class MenuItemBean {

    private final String id;
    private final String alias;

    public MenuItemBean(@Nullable MenuItemBean parent, @NotNull String id, @NotNull String alias) {
        this.id = parent == null ? id : parent.getId() + id;
        this.alias = parent == null ? alias : parent.getAlias() + alias;
    }

    public String getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }
}
