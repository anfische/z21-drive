package z21Drive.broadcasts;

/**
 * Data from feedback devices on R-Bus with address 1 to 10
 */
public class Z21BroadcastLanRmBusDataChanged extends Z21Broadcast{
    private int[][] feedbackStatus = new int[10][8];

    public Z21BroadcastLanRmBusDataChanged(byte[] initArray) {
        super(initArray);
        boundType = BroadcastTypes.LAN_RMBUS_DATACHANGED;
        if (byteRepresentation != null)
            populateFields();
    }

    private void populateFields(){
        if(byteRepresentation[4] == (byte) 0){
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 8; j++){
                    feedbackStatus[i][j] = (int) getBit(byteRepresentation[i+5], j);
                }
            }
        }    	
    }

    public int[][] getFeedbackStatus()
    {
    	return feedbackStatus;
    }
    
    public String getFeedbackStatusString(){
        String feedbackStatusString = "";
        for(int i = 0; i < 10; i++){
            feedbackStatusString += "Feedback Module: " + i;
            System.out.println("Feedback Module: " + i);
            for(int j = 0; j < 0; j++){
                feedbackStatusString += "\n\t" + j + ": " + feedbackStatus[i][j];
            }
        }
        return feedbackStatusString;
    }
    
    private byte getBit(byte Byte, int position){
        return (byte) ((Byte >> position) & 1);
    }
}
