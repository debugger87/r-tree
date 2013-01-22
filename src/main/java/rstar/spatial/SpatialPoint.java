package rstar.spatial;

import rstar.dto.PointDTO;
import rstar.interfaces.IDtoConvertible;

public class SpatialPoint implements IDtoConvertible {
    private int _dimension;
    private float[] _cords;
    private String _oid;

    public SpatialPoint() {
    }

    public SpatialPoint(int dimension) {
        this._dimension = dimension;
        this._oid = "-1";
    }

    public SpatialPoint(float[] cords) {
        this._cords = cords;
        this._dimension = cords.length;
        this._oid = "-1";
    }

    public SpatialPoint(float[] cords, String oid) {
        this._cords = cords;
        this._dimension = cords.length;
        this._oid = oid;
    }

    public SpatialPoint(PointDTO dto) {
        this._cords = dto.coords;
        this._dimension = dto.coords.length;
        this._oid = String.valueOf(dto.oid);
    }

    public int getDimension(){
        return _dimension;
    }

    public void setCords(float[] data){
        this._cords = data;
    }

    public float[] getCords() {
        return _cords;
    }

    public String getOid() {
        return _oid;
    }

    public void setOid(String oid) {
        this._oid = oid;
    }

    /**
     * calculate distance of this point with <pre>otherPoint</pre>
     * @param otherPoint the point from which this point's
     *                   distance is to be calculated
     * @return distance from <pre>otherPoint</pre>
     */
    public float distance(SpatialPoint otherPoint) {
        float[] otherPoints = otherPoint.getCords();
        float distance = 0;
        for (int i = 0; i < _cords.length; i++) {
            float tmp = (_cords[i] - otherPoints[i]) * (_cords[i] - otherPoints[i]);

            distance += tmp;
        }

        return (float)Math.pow(distance, 0.5);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (double cord : _cords) {
            str.append(cord).append(",");
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public PointDTO toDTO() {
        return new PointDTO(Float.parseFloat(_oid), _cords);
    }
}
