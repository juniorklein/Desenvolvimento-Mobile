package br.com.uniftec.todoapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.uniftec.todoapp.R;
import br.com.uniftec.todoapp.model.MapItemMarker;


/**
 * Created by marioklein on 24/11/16.
 */

public class MapFragment extends com.google.android.gms.maps.MapFragment implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap googleMap;
    private MapItemMarker mapItemMarker;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {

        // Configura os dados do MapItemMarker que será armazenado no Marker (pin) que será adicionado ao Map
        mapItemMarker = new MapItemMarker();
        mapItemMarker.setLatitude(-29.173869);
        mapItemMarker.setLongitude(-51.218679);
        mapItemMarker.setTitle("UniFtec");
        mapItemMarker.setDescription("Sede principal UniFtec");

        return super.onCreateView(layoutInflater, viewGroup, bundle);

    }

    @Override
    public void onResume() {
        super.onResume();
        getMapAsync(this); // Inicializa o processo de carga do GoogleMaps
    }

    /*
        Método da interface OnMapReadyCallback que indica que a carga do mapa está completa e já podemos fazer nossas configurações
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap; // Armazena a instância do GoogleMaps
        this.googleMap.setOnInfoWindowClickListener(this); // Configura o objeto de listener que será invocado quando for clicado na InfoWindow
        this.googleMap.setInfoWindowAdapter(this);
        this.googleMap.setOnMapClickListener(this);
        setupView();
    }

    /*
        Método próprio para configurar toda a tela quando o GoogleMaps estiver carregado
     */
    private void setupView() {

        googleMap.clear();

        LatLng latLng = new LatLng(mapItemMarker.getLatitude(), mapItemMarker.getLongitude());

        MarkerOptions options = new MarkerOptions()
                .position(latLng); // Configura a latitude e longitude do Marker que será adicionado
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.city_square)); // Configura o ícone do Marker (pin)

        Marker uniFtectMarker = googleMap.addMarker(options); // Solicita que o serviço do GoogleMaps adicione um novo Marker
        uniFtectMarker.setTag(mapItemMarker); // Armazena no Tag do Marker os dados que geraram esse Marker (MapItemMarker)

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15)); // Configura o zoom da Camera para 15 apontando para a latitude e longitude do Marker

    }


    /*
        Método que, quando necessário, permite montar A VIEW INTEIRA referente a InfoWindow (inclusive "apontador" para o pin)
     */
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    /*
        Método que, quando necessário, permite montar o CONTEÚDO da InfoWindow (somente dados)
     */
    @Override
    public View getInfoContents(Marker marker) {

        MapItemMarker mapItemMarker = (MapItemMarker) marker.getTag(); // Busca os dados do Marker que foram armazenados na tag do Marker

        View infoContents = LayoutInflater.from(getActivity()).inflate(R.layout.map_fragment_info_content, null, false); // Busca a view do Layout

        // Busca e configura os componentes da view
        TextView titleTextView = (TextView) infoContents.findViewById(R.id.title_text_view);
        TextView descriptionTextView = (TextView) infoContents.findViewById(R.id.description_text_view);

        titleTextView.setText(mapItemMarker.getTitle());
        descriptionTextView.setText(mapItemMarker.getDescription());

        return infoContents; // Retorna a View configurada
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        MapItemMarker mapItemMarker = (MapItemMarker) marker.getTag(); // Busca os dados do Marker que foram armazenados na tag do Marker
        Toast.makeText(getActivity(), mapItemMarker.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapClick(LatLng latLng) {

        MapItemMarker mapItemMarker = new MapItemMarker();
        mapItemMarker.setLatitude(latLng.latitude);
        mapItemMarker.setLongitude(latLng.longitude);
        mapItemMarker.setTitle("Fictício");
        mapItemMarker.setDescription("Descrição");


        MarkerOptions options = new MarkerOptions()
                .position(latLng); // Configura a latitude e longitude do Marker que será adicionado
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.city_square)); // Configura o ícone do Marker (pin)

        Marker marker = googleMap.addMarker(options); // Solicita que o serviço do GoogleMaps adicione um novo Marker
        marker.setTag(mapItemMarker);
    }
}
