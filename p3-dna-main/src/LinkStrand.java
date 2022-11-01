public class LinkStrand implements IDnaStrand{
    private class Node {
        String info;
        Node next;

        Node(String x){
            info = x;
        }

        Node(String x, Node node){
            info = x;
	        next = node;
        }
    }

    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;


    public LinkStrand() {
        this("");
    }

    public LinkStrand(String s) {
		initialize(s);
	}

    @Override
    public void initialize(String source) {
        // TODO Auto-generated method stub
        myFirst = new Node(source, null);
        myLast = myFirst;
        mySize = source.length();
        myAppends = 0;
        myIndex = 0;
        myCurrent = myFirst;
        myLocalIndex = 0;
    }

    @Override
    public long size() {
        // TODO Auto-generated method stub
        return mySize;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        // TODO Auto-generated method stub
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String toAppend) {
        // TODO Auto-generated method stub
        if (myFirst.next == null) {
            myFirst.next = new Node(toAppend, null);
            myLast = myFirst.next;
        }
        else {
        myLast.next = new Node(toAppend, null);
        myLast = myLast.next;
        }
        
        mySize += toAppend.length();
        myAppends++;
        return this;
    }

    @Override
	public String toString() {
        StringBuilder myStringBuilder = new StringBuilder();
        Node temp = this.myFirst;
        while (temp != null) {
            myStringBuilder.append(temp.info);
            temp = temp.next;
        }
		return myStringBuilder.toString();
	}

    @Override
    public IDnaStrand reverse() {
        Node rev = new Node(null);
        Node startList = myFirst;

        while (startList != null) {
            Node temp = startList.next;

            StringBuilder builder = new StringBuilder(startList.info);
            builder.reverse();
            Node reversed = new Node(builder.toString(), null);
            Node temp2 = rev;
            rev = reversed;
            rev.next = temp2;
            startList = temp;
        }
        startList = myFirst;
        
        StringBuilder totalReversed = new StringBuilder();

        while (rev.next != null) {
            totalReversed.append(rev.info);
            rev = rev.next;
        }

        return new LinkStrand(totalReversed.toString());
    }

    @Override
    public int getAppendCount() {
        // TODO Auto-generated method stub
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        // TODO Auto-generated method stub
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        
        if (myIndex > index) {
            myIndex = 0;
            myCurrent = myFirst;
            myLocalIndex = 0;
        }
        int numLeft = index - myIndex;

        while (numLeft >= myCurrent.info.length() - myLocalIndex) {
            numLeft -= myCurrent.info.length() - myLocalIndex;
            myLocalIndex = 0;
            myCurrent = myCurrent.next;
        } 
        myLocalIndex += numLeft ;
        myIndex = index;

        return myCurrent.info.charAt(myLocalIndex);
    }
    
}
