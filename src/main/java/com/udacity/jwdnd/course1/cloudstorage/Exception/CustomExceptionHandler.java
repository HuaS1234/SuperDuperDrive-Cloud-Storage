package com.udacity.jwdnd.course1.cloudstorage.Exception;

import com.udacity.jwdnd.course1.cloudstorage.Constant.MsgConstants;
import com.udacity.jwdnd.course1.cloudstorage.Constant.TabConstants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomExceptionHandler {
    //Throwing an exception if file size is > 5MB
    //@ResponseBody
    @ExceptionHandler(value = MultipartException.class)
    public ModelAndView resolveFileUploadException(MultipartException e) {
        ModelAndView modelAndView = new ModelAndView("result.html");
        modelAndView.addObject("error", MsgConstants.fileError_exceedLimit);
        modelAndView.addObject("tabAfterError", TabConstants.file);
        return modelAndView;
    }

    @ExceptionHandler(value = NoteException.class)
    public ModelAndView NoteExceptionHandler(Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView("result.html");
        modelAndView.addObject("error", e.getMessage());
        modelAndView.addObject("tabAfterError", TabConstants.note);
        return modelAndView;
    }

    @ExceptionHandler(value = FileException.class)
    public ModelAndView FileExceptionHandler(Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView("result.html");
        modelAndView.addObject("error", e.getMessage());
        modelAndView.addObject("tabAfterError", TabConstants.file);
        return modelAndView;
    }

}