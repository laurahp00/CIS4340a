// compliant code
// RULE NUM03-J use integer types that can fully represent the possible range of unsigned data
public static long getInteger(DataInputStream is) throws IOException {
  return is.readInt() & 0xFFFFFFFFL; // Mask with 32 one-bits
}
