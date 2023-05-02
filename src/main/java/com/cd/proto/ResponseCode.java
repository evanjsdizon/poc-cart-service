// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CartService.proto

package com.cd.proto;

/**
 * Protobuf enum {@code ResponseCode}
 */
public enum ResponseCode
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>OK = 0;</code>
   */
  OK(0),
  /**
   * <code>BAD_REQUEST = 1;</code>
   */
  BAD_REQUEST(1),
  /**
   * <code>NOT_FOUND = 2;</code>
   */
  NOT_FOUND(2),
  /**
   * <code>ERROR = 3;</code>
   */
  ERROR(3),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>OK = 0;</code>
   */
  public static final int OK_VALUE = 0;
  /**
   * <code>BAD_REQUEST = 1;</code>
   */
  public static final int BAD_REQUEST_VALUE = 1;
  /**
   * <code>NOT_FOUND = 2;</code>
   */
  public static final int NOT_FOUND_VALUE = 2;
  /**
   * <code>ERROR = 3;</code>
   */
  public static final int ERROR_VALUE = 3;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static ResponseCode valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static ResponseCode forNumber(int value) {
    switch (value) {
      case 0: return OK;
      case 1: return BAD_REQUEST;
      case 2: return NOT_FOUND;
      case 3: return ERROR;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<ResponseCode>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      ResponseCode> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<ResponseCode>() {
          public ResponseCode findValueByNumber(int number) {
            return ResponseCode.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.cd.proto.CartServiceOuterClass.getDescriptor().getEnumTypes().get(0);
  }

  private static final ResponseCode[] VALUES = values();

  public static ResponseCode valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private ResponseCode(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:ResponseCode)
}

