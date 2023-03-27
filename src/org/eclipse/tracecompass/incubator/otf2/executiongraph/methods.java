public class Otf2MpiSendEvent {
    private long timestamp;
    private int senderRank;
    private int receiverRank;
    private int messageTag;
    private int messageSize;
    
    public Otf2MpiSendEvent(long timestamp, int senderRank, int receiverRank, int messageTag, int messageSize) {
        this.timestamp = timestamp;
        this.senderRank = senderRank;
        this.receiverRank = receiverRank;
        this.messageTag = messageTag;
        this.messageSize = messageSize;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getSenderRank() {
        return senderRank;
    }

    public int getReceiverRank() {
        return receiverRank;
    }

    public int getMessageTag() {
        return messageTag;
    }

    public int getMessageSize() {
        return messageSize;
    }
}

public class Otf2MpiIsendEvent {
    private long timestamp;
    private int senderRank;
    private int receiverRank;
    private int messageTag;
    private int messageSize;
    
    public Otf2MpiIsendEvent(long timestamp, int senderRank, int receiverRank, int messageTag, int messageSize) {
        this.timestamp = timestamp;
        this.senderRank = senderRank;
        this.receiverRank = receiverRank;
        this.messageTag = messageTag;
        this.messageSize = messageSize;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getSenderRank() {
        return senderRank;
    }

    public int getReceiverRank() {
        return receiverRank;
    }

    public int getMessageTag() {
        return messageTag;
    }

    public int getMessageSize() {
        return messageSize;
    }
}

public class Otf2MpiRecvEvent {
    private long timestamp;
    private int senderRank;
    private int receiverRank;
    private int messageTag;
    private int messageSize;
    
    public Otf2MpiRecvEvent(long timestamp, int senderRank, int receiverRank, int messageTag, int messageSize) {
        this.timestamp = timestamp;
        this.senderRank = senderRank;
        this.receiverRank = receiverRank;
        this.messageTag = messageTag;
        this.messageSize = messageSize;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getSenderRank() {
        return senderRank;
    }

    public int getReceiverRank() {
        return receiverRank;
    }

    public int getMessageTag() {
        return messageTag;
    }

    public int getMessageSize() {
        return messageSize;
    }
}

public class Otf2MpiIrecvEvent {
    private long timestamp;
    private int senderRank;
    private int receiverRank;
    private int messageTag;
    private int messageSize;
    
    public Otf2MpiIrecvEvent(long timestamp, int senderRank, int receiverRank, int messageTag, int messageSize) {
        this.timestamp = timestamp;
        this.senderRank = senderRank;
        this.receiverRank = receiverRank;
        this.messageTag = messageTag;
        this.messageSize = messageSize;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getSenderRank() {
        return senderRank;
    }

    public int getReceiverRank() {
        return receiverRank;
    }

    public int getMessageTag() {
        return messageTag;
    }

    public int getMessageSize() {
        return messageSize;
    }
}

public class Otf2MpiCollectiveEndEvent extends Otf2Event {
    private int communicator;
    private int root;
    private int size;
    private int sendCount;
    private int recvCount;
    
    public Otf2MpiCollectiveEndEvent(long timestamp, int rank, int communicator, int root, int size, int sendCount, int recvCount) {
        super(timestamp, rank);
        this.communicator = communicator;
        this.root = root;
        this.size = size;
        this.sendCount = sendCount;
        this.recvCount = recvCount;
    }

    public int getCommunicator() {
        return communicator;
    }

    public int getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public int getSendCount() {
        return sendCount;
    }

    public int getRecvCount() {
        return recvCount;
    }
}

