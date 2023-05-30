package Classes;

import java.io.File;
import java.util.ArrayList;

public class Testing {

    private Stats _stats;

    private ArrayList<Instance> _instances;

    private String _folderPath;

    private int _instancesCount;

    private int _instancePointsCount;


    public Testing(String folderPath, int instancesCount, int instancePointsCount){
        _folderPath = folderPath;
        _instancePointsCount = instancePointsCount;
        _instancesCount = instancesCount;
        LoadInstances();
        _instancesCount = _instances.size();
    }

    public void Start(){
        for(Instance instance : _instances){
            instance.Init();
            instance.Start();
        }
        _stats = new Stats(_instances);
        _stats.Start();
        _stats.Dashboard();
    }





    private void LoadInstances() {

        ArrayList<Instance> instances = new ArrayList<Instance>();
        int i = 0;

        File folder = new File(_folderPath);

        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (i >= _instancesCount){
                    break;
                }
                if (file.isFile()) {
                    instances.add(new Instance(file.getAbsolutePath(), _instancePointsCount));
                }
                i++;
            }
        }
        _instances = instances;
    }
    
}
