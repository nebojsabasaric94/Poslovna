package bank.nationalBank;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.validation.Valid;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bank.bank.Bank;
import bank.selfCertificate.SelfCertificate;

@RestController
@RequestMapping("/nationalBank")
public class NationalBankController {

	private final NationalBankService nationalBankService;
	//private final SelfCertificateService certificateService;

	@Autowired
	public NationalBankController(final NationalBankService nationalBankService) {
		this.nationalBankService = nationalBankService;
		//this.certificateService = certificateService;
	}

	@GetMapping
	public ResponseEntity<List<NationalBank>> findAll() {
		return new ResponseEntity<>(nationalBankService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody NationalBank nationalBank) {
		nationalBank.setId(null);
		nationalBankService.save(nationalBank);
	}


}
