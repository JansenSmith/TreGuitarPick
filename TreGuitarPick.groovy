

// import pick STL

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
def holeParts = s.extrudeLayerToCSG(10,"insides")
// seperate holes and outsides using layers to differentiate
// The string "2-outsides" represents the layer name in Inkscape
def outsideParts = s.extrudeLayerToCSG(10,"outside")
					.difference(holeParts)