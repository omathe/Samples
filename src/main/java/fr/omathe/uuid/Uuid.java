package fr.omathe.uuid;

public interface Uuid {

	/**
	 * Build a base 64 string from an UUID
	 * @return A String representing a Base64 encoded UUID without the final '=='
	 */
	static String generateShortUuid() {
		return null;


//	        final UUID uuid2 = UUID.randomUUID();
//	        final ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
//	        byteBuffer.putLong(uuid2.getMostSignificantBits());
//	        byteBuffer.putLong(uuid2.getLeastSignificantBits());
//	        String uuid64 = Base64.getUrlEncoder().withoutPadding().encodeToString(byteBuffer.array());
//	        return uuid6;
	}
}
