# 📌 Regular Expressions (Regex) Cheat Sheet

Regular expressions are patterns used to match character combinations in strings.

---

## 🔹 1. Basic Syntax

| Pattern | Meaning                               | Example                          |
| ------- | ------------------------------------- | -------------------------------- |
| `.`     | Any single character (except newline) | `a.c` → matches `"abc"`, `"axc"` |
| `\d`    | Digit (0-9)                           | `\d{3}` → `"123"`                |
| `\D`    | Non-digit                             | `\D+` → `"abc"`                  |
| `\w`    | Word character (a-z, A-Z, 0-9, _)     | `\w+` → `"hello_123"`            |
| `\W`    | Non-word character                    | `\W+` → `"@!$"`                  |
| `\s`    | Whitespace (space, tab, newline)      | `a\sb` → `"a b"`                 |
| `\S`    | Non-whitespace                        | `\S+` → `"abc123"`               |

---

## 🔹 2. Quantifiers

| Pattern | Meaning               | Example                              |
| ------- | --------------------- | ------------------------------------ |
| `*`     | 0 or more             | `ab*` → `"a"`, `"ab"`, `"abb"`       |
| `+`     | 1 or more             | `ab+` → `"ab"`, `"abb"`              |
| `?`     | 0 or 1                | `ab?` → `"a"`, `"ab"`                |
| `{n}`   | Exactly n times       | `\d{3}` → `"123"`                    |
| `{n,}`  | n or more times       | `\d{2,}` → `"12"`, `"12345"`         |
| `{n,m}` | Between n and m times | `a{2,4}` → `"aa"`, `"aaa"`, `"aaaa"` |

---

## 🔹 3. Anchors (position matching)

| Pattern | Meaning             | Example                                     |
| ------- | ------------------- | ------------------------------------------- |
| `^`     | Start of string     | `^abc` → `"abc123"`                         |
| `$`     | End of string       | `xyz$` → `"123xyz"`                         |
| `\b`    | Word boundary       | `\bcat\b` → `"cat"` but not `"concatenate"` |
| `\B`    | Not a word boundary | `\Bcat` → `"educate"`                       |

---

## 🔹 4. Character Classes

| Pattern       | Meaning                   | Example             |
| ------------- | ------------------------- | ------------------- |
| `[abc]`       | Match `a`, `b`, or `c`    | `"a"`, `"b"`        |
| `[^abc]`      | Not `a`, `b`, or `c`      | `"d"`, `"z"`        |
| `[a-z]`       | Range (lowercase letters) | `"a"`, `"z"`        |
| `[A-Z]`       | Uppercase letters         | `"B"`, `"Z"`        |
| `[0-9]`       | Digits                    | `"3"`, `"7"`        |
| `[a-zA-Z0-9]` | Letters + digits          | `"A"`, `"b"`, `"5"` |

---

## 🔹 5. Groups & Alternation

| Pattern   | Meaning             | Example          |                        |
| --------- | ------------------- | ---------------- | ---------------------- |
| `(abc)`   | Capturing group     | `(dog            | cat)`→`"dog"`, `"cat"` |
| `(?:abc)` | Non-capturing group | `(?:ab)+`        |                        |
| `a        | b`                  | OR (alternation) | `"a"` or `"b"`         |

---

## 🔹 6. Escaping Special Characters

If you want to match `.` or `?` literally → escape with `\`.

* Example: `\.` matches `"."`
* Example: `\?` matches `"?"`

---

## 🔹 7. Useful Common Patterns

| Regex            | Meaning                     |                              |
| ---------------- | --------------------------- | ---------------------------- |
| `^\d+$`          | Only digits (whole number)  |                              |
| `^-?\d+$`        | Integer (positive/negative) |                              |
| `^\d+(\.\d+)?$`  | Decimal number              |                              |
| `^[a-zA-Z]+$`    | Only letters                |                              |
| `^[a-zA-Z0-9]+$` | Only alphanumeric           |                              |
| `^\w+@\w+\.\w+$` | Simple email                |                              |
| `^(http          | https)://.*$`               | URL starting with http/https |

---

## 🔹 8. Flags (Modifiers)

| Flag | Meaning                                       | Example                  |
| ---- | --------------------------------------------- | ------------------------ |
| `i`  | Case-insensitive                              | `/abc/i` matches `"ABC"` |
| `m`  | Multi-line (`^` and `$` match line start/end) |                          |
| `s`  | Dotall (`.` matches newlines too)             |                          |
| `g`  | Global match (find all matches)               |                          |

---

✅ Example in **Java**:

```java
import java.util.regex.*;

public class RegexDemo {
    public static void main(String[] args) {
        String text = "User123";
        String pattern = "^[a-zA-Z0-9]+$";  // Only alphanumeric

        boolean matches = Pattern.matches(pattern, text);
        System.out.println(matches); // true
    }
}
```