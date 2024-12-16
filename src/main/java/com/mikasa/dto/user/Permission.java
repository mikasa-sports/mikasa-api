package com.mikasa.dto.user;

public enum Permission {
  ITEM_READ("item_read"),
  ITEM_WRITE("item_write"),
  ORDER_WRITE("order_write"),
  ORDER_READ("order_read"),
  ORDER_ASSIGNED_READ("order_assigned_read"),
  ORDER_ASSIGN("order_assign");

  private final String value;

  public String getValue() {
    return value;
  }

  Permission(String value) {
    this.value = value;
  }
}
