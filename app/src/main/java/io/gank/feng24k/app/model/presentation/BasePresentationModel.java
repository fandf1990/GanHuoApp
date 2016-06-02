package io.gank.feng24k.app.model.presentation;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;


@PresentationModel
public class BasePresentationModel implements HasPresentationModelChangeSupport {

    protected PresentationModelChangeSupport changeSupport;

    public BasePresentationModel(){
        changeSupport = new PresentationModelChangeSupport(this);

    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }

    public void firePropertyChange(String value){
        changeSupport.firePropertyChange(value);
    }
}
