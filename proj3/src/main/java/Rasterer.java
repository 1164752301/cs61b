import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {
        // YOUR CODE HERE
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        // System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        results.put("query_success", true);
        Double ullon = params.get("ullon");
        Double ullat = params.get("ullat");
        Double lrlat = params.get("lrlat");
        Double lrlon = params.get("lrlon");
        Double w = params.get("w");
        Double h = params.get("h");
        Double imageL = 256.0;
        int depth = 0;
        Double ROOT_ULLON = -122.2998046875, ROOT_LRLON = -122.2119140625,
                ROOT_ULLAT = 37.892195547244356, ROOT_LRLAT = 37.82280243352756;
        Double lon = ROOT_LRLON - ROOT_ULLON;
        Double lat = ROOT_ULLAT - ROOT_LRLAT;
        //find the appropriate depth and find the corresponding lon and lat of a tile
        while (LonDPP(lon, imageL) > LonDPP(lrlon - ullon, w) && depth < 7) {
            depth += 1;
            lon = lon / 2;
            lat = lat / 2;
        }
        //divide feets into tiles
        Double left = ullon - ROOT_ULLON;
        Double right = lrlon - ROOT_ULLON;
        Double top = ROOT_ULLAT - ullat;
        Double bottom = ROOT_ULLAT - lrlat;
        if (ullon < ROOT_ULLON || lrlon > ROOT_LRLON
                || ullat > ROOT_ULLAT || lrlat < ROOT_LRLAT) {
            results.put("query_success", false);
        }
        if (ullon >= lrlon || ullat <= lrlat) {
            results.put("query_success", false);
        }
        int xMin = (int) (left / lon);
        int xMax = (int) (right / lon);
        int yMin = (int) (top / lat);
        int yMax = (int) (bottom / lat);
        String[][] render_grid = new String[yMax - yMin + 1][xMax - xMin + 1];
        for(int i = 0; i < render_grid.length; i++) {
            for(int j = 0; j < render_grid[i].length; j++) {
                render_grid[i][j] = "d" + depth + "_x" + (j + xMin)
                        + "_y" + (i + yMin) + ".png";
            }
        }
        results.put("raster_ul_lon", ROOT_ULLON + xMin * lon);
        results.put("raster_ul_lat", ROOT_ULLAT - yMin * lat);
        results.put("raster_lr_lon", ROOT_ULLON + (xMax + 1) * lon);
        results.put("raster_lr_lat", ROOT_ULLAT - (yMax + 1) * lat);
        results.put("depth", depth);
        results.put("render_grid", render_grid);
        return results;
    }

    private Double LonDPP(Double lon, Double w){
        return lon / w;
    }

}
