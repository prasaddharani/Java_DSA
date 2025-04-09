package main.leetcode.hashmapProblems;

import java.util.HashMap;
import java.util.Map;

public class TinyUrl {

    public class Codec {
        Map<String, String> encodeMap;
        Map<String, String> decodeMap;
        String baseUrl;

        Codec() {
            this.encodeMap = new HashMap<>();
            this.decodeMap = new HashMap<>();
            this.baseUrl = "http://tinyurl.com/";
        }


        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String shortUrl = this.baseUrl + this.encodeMap.size();
            encodeMap.put(longUrl, shortUrl);
            decodeMap.put(shortUrl, longUrl);
            return shortUrl;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return decodeMap.getOrDefault(shortUrl, "");
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
}
