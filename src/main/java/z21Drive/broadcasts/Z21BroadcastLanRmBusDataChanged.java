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
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 10; i++){
            sb.append("Module: ");
            sb.append(i);
            sb.append("\n");
            for(int j = 0; j < 8; j++){
                sb.append("Contact ");
                sb.append(j+1);
                sb.append(": ");
                sb.append(feedbackStatus[i][j]);
                sb.append(" | ");
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }
    
    private byte getBit(byte Byte, int position){
        return (byte) ((Byte >> position) & 1);
    }
}
