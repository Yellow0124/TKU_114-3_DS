public class DeliveryTask {
    private String taskId;
    private String address;
    private String itemName;

    public DeliveryTask(String taskId, String address, String itemName) {
        this.taskId = (taskId == null) ? "" : taskId.trim();
        this.address = (address == null || address.trim().isEmpty()) ? "未指定地址" : address.trim();
        this.itemName = (itemName == null || itemName.trim().isEmpty()) ? "一般包裹" : itemName.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public String getAddress() {
        return address;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return String.format("[%s] 物品: %s -> 送達地: %s", taskId, itemName, address);
    }
}