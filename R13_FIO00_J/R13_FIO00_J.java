// noncompliant code, user example
// https://wiki.sei.cmu.edu/confluence/display/java/FIO00-J.+Do+not+operate+on+files+in+shared+directories
String filename = /* Provided by user */;
Path path = new File(filename).toPath();
try {
  BasicFileAttributes attr = Files.readAttributes(
      path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
  Object fileKey = attr.fileKey();
 
  // Check
  if (!attr.isRegularFile()) {
    System.out.println("Not a regular file");
    return;
  }
  // Other necessary checks
 
  // Use
  try (InputStream in = Files.newInputStream(path)) {
 
    // Check
    BasicFileAttributes attr2 = Files.readAttributes(
        path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS
    );
    Object fileKey2 = attr2.fileKey();
    if (!fileKey.equals(fileKey2)) {
      System.out.println("File has been tampered with");
    }
 
    // Read file
  };
} catch (IOException x) {
  // Handle error
}
