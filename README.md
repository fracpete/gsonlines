# gsonlines
Java library for reading/writing JSON lines data.
Automatically compresses/decompresses when the file name ends with `.gz`.


## Reading data

### Stream

The following example reads one line at a time and returns the generated `Map` object:

```java
import com.github.fracpete.gsonlines.StreamReader;

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

The following example reads all the lines in one go and returns an array of `Map` objects: 

```java
import com.github.fracpete.gsonlines.ArrayReader;

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
