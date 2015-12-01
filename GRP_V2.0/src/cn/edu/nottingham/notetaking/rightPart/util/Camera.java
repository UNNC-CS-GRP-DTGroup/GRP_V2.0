package cn.edu.nottingham.notetaking.rightPart.util;


import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

import org.bytedeco.javacv.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.indexer.*;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_calib3d.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;

public class Camera {
    public static void main(String[] args) throws Exception {

        // Preload the opencv_objdetect module to work around a known bug.
        Loader.load(opencv_objdetect.class);

       

        // The available FrameGrabber classes include OpenCVFrameGrabber (opencv_highgui),
        // DC1394FrameGrabber, FlyCaptureFrameGrabber, OpenKinectFrameGrabber,
        // PS3EyeFrameGrabber, VideoInputFrameGrabber, and FFmpegFrameGrabber.
        FrameGrabber grabber = FrameGrabber.createDefault(0);
        grabber.start();

        // CanvasFrame, FrameGrabber, and FrameRecorder use Frame objects to communicate image data.
        // We need a FrameConverter to interface with other APIs (Android, Java 2D, or OpenCV).
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

        // FAQ about IplImage and Mat objects from OpenCV:
        // - For custom raw processing of data, createBuffer() returns an NIO direct
        //   buffer wrapped around the memory pointed by imageData, and under Android we can
        //   also use that Buffer with Bitmap.copyPixelsFromBuffer() and copyPixelsToBuffer().
        // - To get a BufferedImage from an IplImage, or vice versa, we can chain calls to
        //   Java2DFrameConverter and OpenCVFrameConverter, one after the other.
        // - Java2DFrameConverter also has static copy() methods that we can use to transfer
        //   data more directly between BufferedImage and IplImage or Mat via Frame objects.
        IplImage grabbedImage = converter.convert(grabber.grab());
        int width  = grabbedImage.width();
        int height = grabbedImage.height();

        // Objects allocated with a create*() or clone() factory method are automatically released
        // by the garbage collector, but may still be explicitly released by calling release().
        // You shall NOT call cvReleaseImage(), cvReleaseMemStorage(), etc. on objects allocated this way.
        CvMemStorage storage = CvMemStorage.create();

        
        
        Date d = new Date();
		String a = DateFormat.getDateTimeInstance().format(d);
	    a=a.replace('-', ' ');
	    a=a.replace(':', ' ');
        // The OpenCVFrameRecorder class simply uses the CvVideoWriter of opencv_highgui,
        // but FFmpegFrameRecorder also exists as a more versatile alternative.
        FrameRecorder recorder = FrameRecorder.createDefault(a+"output.avi", width, height);
        recorder.start();

        // CanvasFrame is a JFrame containing a Canvas component, which is hardware accelerated.
        // It can also switch into full-screen mode when called with a screenNumber.
        // We should also specify the relative monitor/camera response for proper gamma correction.
        CanvasFrame frame = new CanvasFrame("Camera", CanvasFrame.getDefaultGamma()/grabber.getGamma());

      
        while (frame.isVisible() && (grabbedImage = converter.convert(grabber.grab())) != null) {
            cvClearMemStorage(storage);

           
            Frame grabbedFrame = converter.convert(grabbedImage);
            frame.showImage(grabbedFrame);
            recorder.record(grabbedFrame);
        }
        frame.dispose();
        recorder.stop();
        grabber.stop();
    }
}