# gsonlines
Java library for reading/wriring JSON lines data.

## Reading data

### Stream

```java
import java.io.File;
import java.util.Map;

public class Stream {

  public static void main(String[] args) throws Exception {
    File f = new File("/some/where.jsonl");
    StreamReader r = new StreamReader(f);
    while (r.hasNext()) {
      Map m = r.next(Map.class);
      // do something with the data
    }
    r.close();
  }
}
```

### Batch

```java
import java.io.File;
import java.util.Map;

public class Batch {

  public static void main(String[] args) throws Exception {
    File f = new File("/some/where.jsonl");
    ArrayReader r = new ArrayReader(f);
    Map[] data = r.read(Map.class);
    r.close();
    // do something with the data
  }
}
```
