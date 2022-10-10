package com.hackaton.msuser.sec;

public interface SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String SECRET="GiveMeStar";
    public static final long EXPIRATION=1*24*3600*1000;
    public static final String HEADER_PREFIX="Bearer ";
}
