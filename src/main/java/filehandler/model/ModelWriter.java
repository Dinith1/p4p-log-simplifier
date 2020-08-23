package filehandler.model;

import exceptions.ModelWritingException;
import filehandler.ResourcePath;
import filemodel.Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ModelWriter {

    public void write(Model model) throws ModelWritingException {
        System.out.println("Writing/serializing new model...");
        String path = ResourcePath.MODEL_OBJ_FOLDER_PATH.path() + model.getName() + ".ser";

        try {
            FileOutputStream fo = new FileOutputStream(new File(path));
            ObjectOutputStream oo = new ObjectOutputStream(fo);

            oo.writeObject(model);

            fo.close();
            oo.close();

        } catch (Exception e) {
            throw new ModelWritingException("Could not serialize model to file: " + path + "\n" + e.getMessage());
        }
    }
}
