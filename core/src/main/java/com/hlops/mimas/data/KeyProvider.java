package com.hlops.mimas.data;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 12.04.13
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public interface KeyProvider<Key extends EntityKey> {

    Key getKey();
}
