package com.ddzhuan.manage.controller.jumi.product;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.Dictionary;
import com.ddzhuan.manage.model.jumi.drp.JmGoodsUnit;
import com.ddzhuan.manage.model.jumi.drp.Warehouse;
import com.ddzhuan.manage.model.jumi.product.*;
import com.ddzhuan.manage.service.DictionaryService;
import com.ddzhuan.manage.service.jumi.*;
import com.ddzhuan.manage.tool.UploadFileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.ddzhuan.manage.common.enums.DictionaryEnum.PRICEDECIMALS_DICTIONARY_TYPE;

/**
 * 商品信息
 *
 * @author likeke
 * @date 2020/01/09
 */
@Controller
@RequestMapping("/jumi/product/goods/")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private JmGoodsTypeService jmGoodsTypeService;

    @Autowired
    private JmGoodsUnitService jmGoodsUnitService;

    @Autowired
    private JmGoodsManuFatoryService jmGoodsManuFatoryService;

    @Autowired
    private GoodsSupplierService goodsSupplierService;

    @Autowired
    private GoodsBrandService goodsBrandService;

    @Autowired
    private JmGoodsGroupService jmGoodsGroupService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private JmGoodsExtendService jmGoodsExtendService;

    @Autowired
    private DictionaryService dictionaryService;
    /**
     * 商品列表
     *
     * @param pagination 分页参数
     * @param fields     查询属性
     * @param type       类型：1、实物商品 2、虚拟商品 3、卡包商品 4、原材料
     * @return 商品列表
     */
    @RequestMapping("getlist")
    public String getList(HttpServletRequest request, HttpServletResponse response, Model model, Pagination pagination,
                          GoodsSelectFields fields, @RequestParam(required = false, defaultValue = "1") Integer type) {
        List<Goods> goodsList = goodsService.getGoodsInfoList(fields, type, pagination);
        List<JmGoodsType> jmGoodsTypes = jmGoodsTypeService.pageJmGoodsType(new JmGoodsType(),new Pagination(1, Integer.MAX_VALUE));

        String no = getDefaultNum();
        model.addAttribute("no", no);
        model.addAttribute("type", type);
        model.addAttribute("fields", fields);
        model.addAttribute("goodsList", goodsList);
        model.addAttribute("jmGoodsTypes", jmGoodsTypes);
        return "jumi/product/goods/goodslist";
    }

    private String getDefaultNum() {
        Dictionary dictionary = new Dictionary();
        dictionary.setType(PRICEDECIMALS_DICTIONARY_TYPE.getType());
        dictionary.setCode("pricedecimals");
        dictionary = dictionaryService.getDictionaryByValue(dictionary);
        StringBuilder no = new StringBuilder();
        if (null != dictionary && !StringUtils.isEmpty(dictionary.getValue())) {
            int num = Integer.parseInt(dictionary.getValue());
            if (num <= 0){
                no.append("0");
            }else {
                no.append("0.");
                for (int i = 0; i < num; i++) {
                    no.append("0");
                }
            }
        }
        return no.toString();
    }

    /**
     * 商品新增页面
     *
     * @param type 类型：1、实物商品 2、虚拟商品 3、卡包商品 4、原材料
     * @return 新增页面
     */
    @RequestMapping("addgoods")
    public String addGoods(HttpServletRequest request, HttpServletResponse response, Model model,
                           @RequestParam(required = false, defaultValue = "1") Integer type) {
        getGoodsModel(model,type);
        return "jumi/product/goods/addgoods";
    }

    /**
     * 商品保存
     *
     * @param goods 商品信息
     * @return message
     */
    @RequestMapping("savegoods")
    @ResponseBody
    public BaseResultInfo saveGoods(HttpServletRequest request, HttpServletResponse response, Goods goods) {
        BaseResultInfo result = goodsService.saveGoodsInfo(goods);
        return result;
    }

    /**
     * 编辑商品信息
     *
     * @param id   商品信息id
     * @return 编辑页面
     */
    @RequestMapping("editgoods")
    public String editGoods(HttpServletRequest request, HttpServletResponse response,
                            Model model, Long id, Integer oType) {
        // TODO 依赖分类列表、单位、仓库、生产厂家、供货商、商品分组、商品品牌
        Goods goods = goodsService.getGoodsInfoById(id);
        model.addAttribute("goods", goods);
        getGoodsModel(model,goods.getType());
        if (OperateTypeEnum.EDIT.code.equals(oType)) {
            return "jumi/product/goods/editgoods";
        }else {
            return "jumi/product/goods/goodsdetail";
        }
    }

    /**
     * 修改商品信息
     *
     * @param goods 商品信息
     * @return message
     */
    @RequestMapping("updategoods")
    @ResponseBody
    public BaseResultInfo updateGoods(HttpServletRequest request, HttpServletResponse response,
                                      Model model, Goods goods) {
        BaseResultInfo result = goodsService.modifyGoodsInfo(goods);
        return result;
    }

    /**
     * 删除商品记录
     *
     * @param id 商品id
     * @param type 类型：1、实物商品 2、虚拟商品 3、卡包商品 4、原材料
     * @return message
     */
    @RequestMapping("deletegoods")
    @ResponseBody
    public BaseResultInfo deleteGoods(HttpServletRequest request, HttpServletResponse response,
                                      Long id, Integer type) {
        // TODO 完善接口代码
        // TODO 返回message时携带type参数，用于定位列表
        BaseResultInfo result = new BaseResultInfo();
        try {
            goodsService.removeGoodsInfoById(id);
            HashMap<String, Object> extInfo = new HashMap<>();
            extInfo.put("type", type);
            result.setSuccess(true);
            result.setMsg("删除商品成功");
            result.setExtInfo(extInfo);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("删除商品异常");
        }
        return result;
    }


    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping("uploadimg")
    @ResponseBody
    public BaseResultInfo editImg(@RequestParam(value = "file", required = false) MultipartFile file){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        try{
            List<Map<String,Object>>  fileList = new ArrayList<>();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("filename",file.getOriginalFilename());
            map.put("stream",file.getInputStream());
            fileList.add(map);
            baseResultInfo = UploadFileTool.getInstance().uploadLicenImg(fileList,"linkshow/GOODS/");
        }catch (IOException e){
            e.printStackTrace();
        }
        return  baseResultInfo;
    }

    private void getGoodsModel(Model model, Integer type) {
        List<JmGoodsType> jmGoodsTypes = jmGoodsTypeService.pageJmGoodsType(new JmGoodsType(),new Pagination(1, Integer.MAX_VALUE));
        List<JmGoodsUnit>  jmGoodsUnits = jmGoodsUnitService.pageJmGoodsUnit(new JmGoodsUnit(),new Pagination(1,Integer.MAX_VALUE));
        List<JmGoodsManuFatory> jmGoodsManuFatories = jmGoodsManuFatoryService.pageJmGoodsManuFatory(new JmGoodsManuFatory(),new Pagination(1,Integer.MAX_VALUE));
        List<Warehouse> warehouses = warehouseService.getWarehouseList(null,new Pagination(1,Integer.MAX_VALUE));
        List<GoodsSupplier> goodsSuppliers = goodsSupplierService.getGoodsSupplierList(new GoodsSupplier(),new Pagination(1,Integer.MAX_VALUE));
        List<GoodsBrand> goodsBrands = goodsBrandService.queryGoodsBrandByName(null,new Pagination(1,Integer.MAX_VALUE));
        List<JmGoodsExtend> jmGoodsExtends = new ArrayList<>();
        // 实物商品和虚拟商品才有自定义字段
        if (1 == type || 2 == type) {
            JmGoodsExtend extend = new JmGoodsExtend();
            extend.setGoodsType(type);
            jmGoodsExtends = jmGoodsExtendService.pageJmGoodsExtend(extend,new Pagination(1,Integer.MAX_VALUE));
        }
        List<GoodsGroup> goodsGroups = jmGoodsGroupService.queryGoodsGroupBy(null,new Pagination(1,Integer.MAX_VALUE));
        jmGoodsExtends = jmGoodsExtends.stream().peek(v -> {
            if (!StringUtils.isEmpty(v.getFieldValue())) {
                v.setItems(Arrays.asList(v.getFieldValue().split(",")));
            }

        }).collect(Collectors.toList());

        String no = getDefaultNum();
        model.addAttribute("no",no);
        model.addAttribute("type",type);
        model.addAttribute("jmGoodsTypes",jmGoodsTypes);
        model.addAttribute("jmGoodsExtends",jmGoodsExtends);
        model.addAttribute("jmGoodsUnits",jmGoodsUnits);
        model.addAttribute("jmGoodsManuFatories",jmGoodsManuFatories);
        model.addAttribute("warehouses",warehouses);
        model.addAttribute("goodsSuppliers",goodsSuppliers);
        model.addAttribute("goodsBrands",goodsBrands);
        model.addAttribute("goodsGroups",goodsGroups);
    }
}
