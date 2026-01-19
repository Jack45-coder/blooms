package in.codingage.blooms.utlils;

public class RandomIdUtils {
    public static String generateRandom(int length){
        StringBuilder sb = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for(int i = 0; i < length; i++){
            int index = (int) (Math.random()*characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
}
