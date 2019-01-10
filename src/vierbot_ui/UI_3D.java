package vierbot_ui;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

public class UI_3D {

    SimpleUniverse universe = new SimpleUniverse();
    BranchGroup group = new BranchGroup();
    ColorCube cube = new ColorCube(0.3);
    TransformGroup GT = new TransformGroup();
    Transform3D transform = new Transform3D();

    public UI_3D() {
        GT.setTransform(transform);
        GT.addChild(cube);
        group.addChild(GT);
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(group);
    }
}
