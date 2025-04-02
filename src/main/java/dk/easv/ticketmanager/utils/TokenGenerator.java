package dk.easv.ticketmanager.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator
{
  public static String generateSessionToken() {
    SecureRandom random = new SecureRandom();
    byte[] bytes = new byte[32];
    random.nextBytes(bytes);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
  }
}
