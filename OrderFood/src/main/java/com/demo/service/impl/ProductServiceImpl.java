package com.demo.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entity.Products;
import com.demo.model.PagingSearchFilterProduct;
import com.demo.model.entity.ProductSpecification;
import com.demo.repository.ProductRepository;
import com.demo.service.ProductService;
import com.demo.utils.Constant;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepo;
	
	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


	@Override
	public Products createProduct(Products products) {
		// TODO Auto-generated method stub
		products.setCreateDate(new Date());
		products.setUpdateDate(new Date());
		if(!products.getMultipartFile().isEmpty() ) {
			String  imagesName = uploadFile(products.getMultipartFile());
			products.setImages(imagesName);
			System.out.println(imagesName);
		}
		return productRepo.save(products);
	}
	private String uploadFile(MultipartFile multipartFile)   { 
		String imagesName = null;
		try {
			imagesName = System.currentTimeMillis() + multipartFile.getOriginalFilename();
			File file = new File(Constant.PATH_UPLOAD_IMAGES + imagesName);
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
			bufferedOutputStream.write(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 
			log.warn("Upload file faild !!");
		}
		return "/upload/"+imagesName;
	}

	@Override
	public Products updateProduct(Products products) {
		// TODO Auto-generated method stub
		products.setUpdateDate(new Date());
		if(!products.getMultipartFile().isEmpty()) {
			String  imagesName = uploadFile(products.getMultipartFile());
			products.setImages(imagesName);
		}
		return productRepo.save(products);
	}

	@Override
	public void deleteProducts(Products products) {
		// TODO Auto-generated method stub
		productRepo.delete(products);
	}

	@Override
	public Products getById(long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).orElse(null);
	}

	@Override
	public Products getByName(String name) {
		// TODO Auto-generated method stub
		return productRepo.findByName(name);
	}

	@Override
	public List<Products> getAll() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Page<Products> getAll(PagingSearchFilterProduct psfp) {
		// TODO Auto-generated method stub
		return productRepo.findAll(new ProductSpecification(psfp.getKeyword(), psfp.getCateId(),psfp.getStatus()),
									PageRequest.of(psfp.getPage(), psfp.getPageSize()));
	}
	

}
