import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ReportDialogComponent} from '../dialogs/report-dialog/report-dialog.component';
import {ReportService} from '../services/report.service';
import {Report} from '../interfaces/report.interface';
import {MeasurementService} from "../services/measurement.service";
import {Measurement} from '../interfaces/measurement.interface';

declare var ol: any;

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  details = {
    Wroclaw: {
      latitude: 17.0385,
      longitude: 51.1079,
      zoom: 14,
    },
    Krakow: {
      latitude: 19.145,
      longitude: 51.9194,
      zoom: 7,
    },
    Warszawa: {
      latitude: 19.145,
      longitude: 51.9194,
      zoom: 7,
    },
  };
  reports: Report[] = [];
  measurements: Map<Report, Measurement> = new Map();

  map: any;
  picked: string | null;

  constructor(public dialog: MatDialog, private _reportService: ReportService, private _measurementService: MeasurementService) {}

  ngOnInit(): void {

    this.map = new ol.Map({
      target: 'map',
      layers: [
        new ol.layer.Tile({
          source: new ol.source.OSM()
        })
      ],
      view: new ol.View({
        center: ol.proj.fromLonLat([this.details.Wroclaw.latitude, this.details.Wroclaw.longitude]),
        zoom: this.details.Wroclaw.zoom,
      })
    });

    this._reportService.fetchReports().subscribe(reports => {
      this.reports = reports;
      reports.forEach((report: Report) => {
        this.addReportPoint(report);
        this._measurementService.fetchNearestMeasurement(report.latitude, report.longitude, 5).subscribe(measurement => {
          this.measurements.set(report, measurement);
          this.addMeasurementCircle(report, measurement);
        })
      });
    })

  }

  addReportPoint(report: Report) {
    const icon = new ol.style.Icon({
      anchor: [0.5, 0.5],
      anchorXUnits: "fraction",
      anchorYUnits: "fraction",
      src: "https://upload.wikimedia.org/wikipedia/commons/e/ec/RedDot.svg",
    });
    var vectorLayer = new ol.layer.Vector({
      source: new ol.source.Vector({
        features: [new ol.Feature({
          geometry: new ol.geom.Point(this.transformCoords(report)),
          id: report.id,
        })]
      }),
      style: new ol.style.Style({
        image: icon
      })
    });
    this.map.addLayer(vectorLayer);
    const c = (e) => {
      var feature = this.map.forEachFeatureAtPixel(e.pixel,
        (f) => {
          for (let i = 0; i < this.reports.length; i++) {
            if (f.get('id') === this.reports[i].id) {
              return f;
            }
          }
        });
      if (feature && this.picked !== feature.get('id')) {
        this.picked = feature.get('id');
        const element = this.reports.find((elem) => elem.id === this.picked);
        // alert("Average air quality pointers in this area" + this.measurements.get(report).current.values.map(value => {
        //   var str: string = "\n" + value.name + " - " + value.value + "\n";
        //   this.measurements.get(report).current.indexes.forEach(index => {
        //     str = str + index.name + " " + index.value;
        //   })
        //   return str;
        // }));
        const dialogRef = this.dialog.open(ReportDialogComponent, {
          data: element
        });
        dialogRef.afterClosed().subscribe(() => {
          this.picked = null;
        })
      }
    }
    const h = c.bind(this);
    this.map.on('click', h);
  }

  transformCoords(report: Report) {
    return ol.proj.transform([report.longitude, report.latitude], 'EPSG:4326', 'EPSG:3857');
  }

  addMeasurementCircle(report: Report, measurement: Measurement) {
    var myStlye = new ol.style.Style ({
      fill: new ol.style.Fill({
        color: 'rgba(255,100,50,0.5)'
      }),
      stroke: new ol.style.Stroke({
        color: 'blue',
        width: 3
      }),
      text: new ol.style.Text({
        textAlign: "Start",
        textBaseline: "Middle",
        font: 'Normal 12px Arial',
        text: 'CHUJ CI W DUPE',
        fill: new ol.style.Fill({
          color: '#ffa500'
        }),
        stroke: new ol.style.Stroke({
          color: '#000000',
          width: 3
        }),
        offsetX: -45,
        offsetY: 0,
        rotation: 0
      })
    });
    var vectorLayer = new ol.layer.Vector({
      source: new ol.source.Vector({
        features: [new ol.Feature({
          geometry: new ol.geom.Circle(this.transformCoords(report), 500),
          id: 'measurement_' + report.id
        })]
      }),
      style: function(feature) {
        myStlye.getText().setText('chuj')
        return myStlye;
      }
    })
    this.map.addLayer(vectorLayer);
  }

}

