package adht.structures;
// funzione di hashing (copiata da internet)
	public final class MurmurHashx32 {

	    // MurmurHash3 32-bit x86
	    public static int hash32(byte[] data) {
	        return hash32(data, 0, data.length, 0);
	    }

	    public static int hash32(byte[] data, int offset, int len, int seed) {
	        final int c1 = 0xcc9e2d51;
	        final int c2 = 0x1b873593;

	        int h1 = seed;
	        int roundedEnd = offset + (len & 0xfffffffc);  // round down to 4 byte block

	        for (int i = offset; i < roundedEnd; i += 4) {
	            int k1 = ((data[i] & 0xff)) |
	                     ((data[i + 1] & 0xff) << 8) |
	                     ((data[i + 2] & 0xff) << 16) |
	                     ((data[i + 3] & 0xff) << 24);

	            k1 *= c1;
	            k1 = Integer.rotateLeft(k1, 15);
	            k1 *= c2;

	            h1 ^= k1;
	            h1 = Integer.rotateLeft(h1, 13);
	            h1 = h1 * 5 + 0xe6546b64;
	        }

	        int k1 = 0;

	        switch (len & 0x03) {
	            case 3:
	                k1 = (data[roundedEnd + 2] & 0xff) << 16;
	            case 2:
	                k1 |= (data[roundedEnd + 1] & 0xff) << 8;
	            case 1:
	                k1 |= (data[roundedEnd] & 0xff);
	                k1 *= c1;
	                k1 = Integer.rotateLeft(k1, 15);
	                k1 *= c2;
	                h1 ^= k1;
	        }

	        h1 ^= len;

	        // finalization mix
	        h1 ^= (h1 >>> 16);
	        h1 *= 0x85ebca6b;
	        h1 ^= (h1 >>> 13);
	        h1 *= 0xc2b2ae35;
	        h1 ^= (h1 >>> 16);

	        return h1;
	    }

	    // Utility: hash di una stringa
	    public static int hash32(String s) {
	        return hash32(s.getBytes());
	    }
	}


