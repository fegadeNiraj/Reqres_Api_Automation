package Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonReader
{
    public static JsonNode rootNode;

    static {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(
                    new File("Resources/TestData/testdata.json")
            );
        }catch (Exception e)
        {
            throw new RuntimeException("Failed to read testdata.json");
        }
    }

    public static JsonNode getRoot()
    {
        return rootNode;
    }
}
