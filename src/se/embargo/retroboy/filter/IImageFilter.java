package se.embargo.retroboy.filter;

import java.nio.IntBuffer;

import android.graphics.Bitmap;

public interface IImageFilter {
	public class ImageBuffer {
		/**
		 * Raw image data, typically YUV format
		 */
		public byte[] frame;
		
		/**
		 * Width and height of the raw data
		 */
		public final int framewidth, frameheight;
		
		/**
		 * Sequence number of this frame 
		 */
		public long frameseq;
		
		public IntBuffer image;
		public int imagewidth, imageheight;

		public Bitmap bitmap;

		public int[] histogram = new int[256];
		public int threshold = 128;
		
		public ImageBuffer(byte[] frame, int framewidth, int frameheight) {
			this.frame = frame;
			this.framewidth = framewidth;
			this.frameheight = frameheight;
		}

		public ImageBuffer(int framewidth, int frameheight) {
			this(null, framewidth, frameheight);
		}
		
		public ImageBuffer(Bitmap input) {
			this(null, input.getWidth(), input.getHeight());
			imagewidth = framewidth;
			imageheight = frameheight;
			image = IntBuffer.wrap(new int[imagewidth * imageheight + imagewidth * 4]);
			bitmap = input;
			bitmap.copyPixelsToBuffer(image);
		}
	}
	
	public void accept(ImageBuffer buffer);
}
