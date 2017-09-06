package com.huidos.mangooo.service.reports;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.huidos.mangooo.model.DetalleVenta;
import com.huidos.mangooo.model.Estado;
import com.huidos.mangooo.model.GastoCorte;
import com.huidos.mangooo.model.Municipio;
import com.huidos.mangooo.model.Producto;
import com.huidos.mangooo.model.Venta;
import com.huidos.mangooo.repository.DetalleVentaRepository;
import com.huidos.mangooo.repository.EstadoRepository;
import com.huidos.mangooo.repository.GastoCorteRepository;
import com.huidos.mangooo.repository.MunicipioRepository;
import com.huidos.mangooo.repository.ProductoRepository;
import com.huidos.mangooo.repository.VentaRepository;
import com.huidos.mangooo.service.reports.dto.ReporteOriginal;

@Service
public class ReporteService {

	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private DetalleVentaRepository detalleVentaRepository;
	@Autowired
	private GastoCorteRepository gastoCorteRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private MunicipioRepository municipioRepository;

	public List<ReporteOriginal> getOriginalReporte() {
		List<ReporteOriginal> lstReporteOriginal = new ArrayList<>();
		Iterable<DetalleVenta> detalleVentas = detalleVentaRepository.findAll();
		ReporteOriginal repoOrig;
		GastoCorte gastoCorte;
		Venta venta;
		Estado estado;
		Producto producto;
		Municipio municipio;
		for (DetalleVenta detalleVenta : detalleVentas) {
			repoOrig = new ReporteOriginal();
			venta = ventaRepository.findOne(detalleVenta.getId().getIdVenta());
			gastoCorte = gastoCorteRepository.findOne(detalleVenta.getId().getIdGastoCorte());
			producto = productoRepository.findOne(detalleVenta.getId().getIdProducto());
			estado = estadoRepository.findOne(gastoCorte.getIdGastoCorte());
			municipio=municipioRepository.findOne(gastoCorte.getMunicipio().getIdMunicipio());
			repoOrig.setChequeA(venta.getCliente().getNombre());
			repoOrig.setEstado(estado.getNombre());
			repoOrig.setMunicipio(municipio.getNombre());
			repoOrig.setVariedades(producto.getVariedad());
			repoOrig.setTotalCajas((int) detalleVenta.getCajas());
			repoOrig.setTotalKilos(detalleVenta.getKilos());
			repoOrig.setKilosPrmoedio(repoOrig.getTotalKilos() / repoOrig.getTotalCajas());
			repoOrig.setPrecio((detalleVenta.getPrecioKilo().doubleValue()));
			repoOrig.setImpFruta(repoOrig.getTotalKilos() * detalleVenta.getPrecioKilo().doubleValue());
			repoOrig.setGastoCorte(gastoCorte.getGastoCortePercent().doubleValue());
			repoOrig.setImpGastoCorte(repoOrig.getTotalKilos() * gastoCorte.getGastoCortePercent().doubleValue());
			if(null != venta.getRetProd())
				repoOrig.setRetProd(venta.getRetProd().doubleValue());
			else
				repoOrig.setRetProd(0);
			repoOrig.setTotalChFruta(repoOrig.getImpFruta() - repoOrig.getRetProd());
			repoOrig.setTotal(repoOrig.getImpFruta() + repoOrig.getImpGastoCorte());
			repoOrig.setFormaDePago(detalleVenta.getFormaPago());
			lstReporteOriginal.add(repoOrig);
		}
		return lstReporteOriginal;
	}

	public List<ReporteOriginal> getVentasSemanal() {
		List<ReporteOriginal> lstVentasSemanal = new ArrayList<>();
		Pageable topTen = new PageRequest(0, 10);
		Iterable<DetalleVenta> detalleVentas = detalleVentaRepository.findAll(topTen);
		ReporteOriginal repoOrig;
		GastoCorte gastoCorte;
		Venta venta;
		Estado estado;
		Producto producto;
		Municipio municipio;
		for (DetalleVenta detalleVenta : detalleVentas) {
			repoOrig = new ReporteOriginal();
			venta = ventaRepository.findOne(detalleVenta.getId().getIdVenta());
			gastoCorte = gastoCorteRepository.findOne(detalleVenta.getId().getIdGastoCorte());
			producto = productoRepository.findOne(detalleVenta.getId().getIdProducto());
			estado = estadoRepository.findOne(gastoCorte.getEstado().getIdEstado());
			municipio=municipioRepository.findOne(gastoCorte.getMunicipio().getIdMunicipio());
			repoOrig.setChequeA(venta.getCliente().getNombre());
			repoOrig.setVariedades(producto.getNombre()+" "+producto.getVariedad());
			repoOrig.setEstado(estado.getNombre());
			repoOrig.setMunicipio(municipio.getNombre());
			repoOrig.setTotalCajas((int) detalleVenta.getCajas());
			repoOrig.setTotalKilos(detalleVenta.getKilos());
			repoOrig.setKilosPrmoedio(repoOrig.getTotalKilos() / repoOrig.getTotalCajas());
			repoOrig.setPrecio((detalleVenta.getPrecioKilo().doubleValue()));
			repoOrig.setImpFruta(repoOrig.getTotalKilos() * detalleVenta.getPrecioKilo().doubleValue());
			repoOrig.setGastoCorte(gastoCorte.getGastoCortePercent().doubleValue());
			repoOrig.setImpGastoCorte(repoOrig.getTotalKilos() * gastoCorte.getGastoCortePercent().doubleValue());
			if(null != venta.getRetProd())
				repoOrig.setRetProd(venta.getRetProd().doubleValue());
			else
				repoOrig.setRetProd(0);
			repoOrig.setTotalChFruta(repoOrig.getImpFruta() - repoOrig.getRetProd());
			repoOrig.setTotal(repoOrig.getImpFruta() + repoOrig.getImpGastoCorte());
			repoOrig.setFormaDePago(detalleVenta.getFormaPago());
			lstVentasSemanal.add(repoOrig);
		}
		return lstVentasSemanal;
	}

}
