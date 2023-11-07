# gsonlines
Java library for reading/writing JSON lines data.
Automatically compresses/decompresses when the file name ends with `.gz`.

## Examples

### Stream

The following example reads one line at a time and writes one line at a time as well:

```java
import com.github.fracpete.gsonlines.StreamReader;
import com.github.fracpete.gsonlines.StreamWriter;

import java.io.File;
import java.util.Map;

public class Stream {

  public static void main(String[] args) throws Exception {
    File fin = new File("/some/where/in.jsonl.gz");
    File fout = new File("/some/where/out.jsonl.gz");
    StreamReader r = new StreamReader(fin);
    StreamWriter w = new StreamWriter(fout);
    while (r.hasNext()) {
      Map m = r.next(Map.class);
      // do something with the data
      w.write(m);
    }
    r.close();
    w.close();
  }
}
```

### Batch

The following example reads all the lines in one go, processes them and then writes them again to another file: 

```java
import com.github.fracpete.gsonlines.ArrayReader;
import com.github.fracpete.gsonlines.ArrayWriter;

import java.io.File;
import java.util.Map;

public class Batch {

  public static void main(String[] args) throws Exception {
    File fin = new File("/some/where/in.jsonl");
    File fout = new File("/some/where/out.jsonl");
    ArrayReader r = new ArrayReader(fin);
    ArrayWriter w = new ArrayWriter(fout);
    Map[] data = r.read(Map.class);
    // do something with the data
    w.write(data);
    r.close();
    w.close();
  }
}
```
