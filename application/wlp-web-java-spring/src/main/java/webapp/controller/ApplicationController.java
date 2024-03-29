package webapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import webapp.tier.cache.memcached.GetMemcached;
import webapp.tier.cache.memcached.SetMemcached;
import webapp.tier.cache.redis.GetRedis;
import webapp.tier.cache.redis.PublishRedis;
import webapp.tier.cache.redis.SetRedis;
import webapp.tier.db.mysql.DeleteMysql;
import webapp.tier.db.mysql.InsertMysql;
import webapp.tier.db.mysql.SelectMysql;
import webapp.tier.mq.rabbitmq.GetRabbitmq;
import webapp.tier.mq.rabbitmq.PutRabbitmq;
import webapp.tier.mq.rabbitmq.PutRabbitmqConsumer;

@Controller
public class ApplicationController {
	Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping("InsertMysql")
	public String insertDb(Model model) throws Exception {

		logger.info("InsertMysql");
		InsertMysql insmsg = new InsertMysql();
		String msg = insmsg.insertMysql();

		model.addAttribute("insertMysql", msg);

		return "insertmysql";
	}

	@RequestMapping("SelectMysql")
	public String selectMysql(Model model) throws SQLException, NamingException {

		logger.info("SelectMysql");
		SelectMysql insmsg = new SelectMysql();
		List<String> allMessage = insmsg.selectMsg();

		model.addAttribute("allMessageList", allMessage);

		return "selectmysql";
	}

	@RequestMapping("DeleteMysql")
	public String deleteMysql() throws SQLException, NamingException {

		logger.info("DeleteMysql");
		DeleteMysql insmsg = new DeleteMysql();
		insmsg.deleteMsg();

		return "deletemysql";
	}

	@RequestMapping("GetRabbitmq")
	public String getMq(Model model) throws Exception {

		logger.info("GetRabbitmq");
		GetRabbitmq getmq = new GetRabbitmq();
		String telegram = getmq.getMessageQueue();

		model.addAttribute("getRabbitmq", telegram);

		return "getrabbitmq";
	}

	@RequestMapping("PutRabbitmq")
	public String putMq(Model model) throws IOException, TimeoutException {

		logger.info("PutRabbitmq");
		PutRabbitmq putmq = new PutRabbitmq();
		String telegram = putmq.putMessageQueue();

		model.addAttribute("putRabbitmq", telegram);

		return "putrabbitmq";
	}

	@RequestMapping("PutRabbitmqConsumer")
	public String putMqBatch(Model model) throws IOException, TimeoutException {

		logger.info("PutRabbitmqConsumer");
		PutRabbitmqConsumer putmqb = new PutRabbitmqConsumer();
		String telegram = putmqb.putMessageQueueConsumer();

		model.addAttribute("putRabbitmqConsumer", telegram);

		return "putrabbitmqconsumer";
	}

	@RequestMapping("GetMemcached")
	public String getMemcached(Model model) {

		logger.info("GetMemcached");
		GetMemcached getcache = new GetMemcached();
		String cache = getcache.getMemcached();

		model.addAttribute("getMemcached", cache);

		return "getmemcached";
	}

	@RequestMapping("SetMemcached")
	public String setMemcached(Model model) throws Exception {

		logger.info("SetMemcached");
		SetMemcached setcache = new SetMemcached();
		String cache = setcache.setMemcached();

		model.addAttribute("setMemcached", cache);

		return "setmemcached";
	}

	@RequestMapping("GetRedis")
	public String getRedis(Model model) {

		logger.info("GetRedis");
		GetRedis getcache = new GetRedis();
		List<String> cache = getcache.getRedis();

		model.addAttribute("getRedisList", cache);

		return "getredis";
	}

	@RequestMapping("SetRedis")
	public String setRedis(Model model) {

		logger.info("SetRedis");
		SetRedis setcache = new SetRedis();
		String cache = setcache.setRedis();

		model.addAttribute("setRedis", cache);

		return "setredis";
	}

	@RequestMapping("PublishRedis")
	public String publishRedis(Model model) {

		logger.info("PublishRedis");
		PublishRedis publishcache = new PublishRedis();
		String cache = publishcache.publishRedis();

		model.addAttribute("publishRedis", cache);

		return "publishredis";
	}
}
