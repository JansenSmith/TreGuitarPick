

// import pick STL
File pickSTL = ScriptingEngine.fileFromGit(
	"https://github.com/JansenSmith/TreGuitarPick.git",
	"pick.stl");
// Load the .CSG from the disk and cache it in memory
CSG pick  = Vitamins.get(pickSTL);

// import sig SVG
File f = ScriptingEngine
	.fileFromGit(
		"https://github.com/JansenSmith/TreGuitarPick.git",//git repo URL
		"main",//branch
		"sig.SVG"// File from within the Git repo
	)
println "Extruding SVG "+f.getAbsolutePath()
SVGLoad s = new SVGLoad(f.toURI())
println "Layers= "+s.getLayers()
// A map of layers to polygons
HashMap<String,List<Polygon>> polygonsByLayer = s.toPolygons()
// extrude all layers to a map to 10mm thick
HashMap<String,ArrayList<CSG>> csgByLayers = s.extrudeLayers(10)
// extrude just one layer to 10mm
// The string "1-holes" represents the layer name in Inkscape
def insideParts = s.extrudeLayerToCSG(10,"insides")
// seperate holes and outsides using layers to differentiate
// The string "2-outsides" represents the layer name in Inkscape
def outsideParts = s.extrudeLayerToCSG(10,"outside")

CSG sig = outsideParts.difference(insideParts)

return sig