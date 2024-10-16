/**
 * funcion para descargar los reportes donde se una la libreria @CHAR
 * en esta funcion @descargaReportes 
 * se captura el contenedor el cual se quiere mostrar en el pdf y se le dan los datos
 * necesarios para que el pueda generarse con ciertos parametros, como
 * la margen que va a tener el documento
 * el tipo que se va a generar
 * la escala
 * y la hoja en la cual se va a generar
 */

export default function descargaReporte(){
    //se captura el contenedor que se desea generar
    const element = document.querySelector('#reporte');
    //creamos el objeto para posteriormente pasarselo a la libreria
    const opt = {
    margin:       0.4,  // es la margen la cual va a tener las paginas capturadas
    filename:     'reporte_completo.pdf', // el nombre del documento que se generó
    image:        { type: 'jpeg', quality: 0.98 }, 
    html2canvas:  { scale: 8, useCORS: true },  // la escala sirve para aumentar la resolucion de la captura
    jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' } //formato de hoja e cual se genera
    };

    //llamamos algunas funciones de la libreria y le pasamos las configuraciones 
    //que acabamos de proporcionar, el contenedor y las configuraciones 
    html2pdf().from(element).set(opt).save();
}