public class Q10_RecordParser {
    public static void main(String[] args) {
        String[] records = {
                "A101|Keyboard|3|850",
                "A102|Mouse|-1|500",
                "broken data",
                "A103|Monitor|2|4200",
                "A104||1|300"
        };
        for (String record : records) {
            System.out.println(record + " -> " + calculateRecordTotal(record));
        }
        System.out.println("合法筆數：" + countValidRecords(records));
        System.out.println("總金額：" + calculateGrandTotal(records));
    }

    public static boolean isValidRecord(String record) {
        if (record == null || record.trim().isEmpty()) {
            return false;
        }

        String[] parts = record.split("\\|");

        if (parts.length != 4) {
            return false;
        }

        String code = parts[0].trim();
        String name = parts[1].trim();
        if (code.isEmpty() || name.isEmpty()) {
            return false;
        }

        try {
            int quantity = Integer.parseInt(parts[2].trim());
            int price = Integer.parseInt(parts[3].trim());

            if (quantity <= 0 || price < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static int calculateRecordTotal(String record) {
        if (!isValidRecord(record)) {
            return -1;
        }

        String[] parts = record.split("\\|");
        int quantity = Integer.parseInt(parts[2].trim());
        int price = Integer.parseInt(parts[3].trim());

        return quantity * price;
    }

    public static int countValidRecords(String[] records) {
        if (records == null) {
            return 0;
        }
        int count = 0;
        for (String record : records) {
            if (isValidRecord(record)) {
                count++;
            }
        }
        return count++;
    }

    public static int calculateGrandTotal(String[] records) {
        int total = 0;
        if (records == null) {
            return 0;
        }
        for (String record : records) {
            if (isValidRecord(record)) {
                int recordTotal = calculateRecordTotal(record);
                if (recordTotal != -1) {
                    total += recordTotal;
                }
            }
        }
        return total;
    }
}
