const express = require('express');
const router = express.Router();
const {createRoapmap} = require('../controllers/roapmapController');
const {getRoadmap,getAllRoadmap} = require('../controllers/roapmapController');

router.post('/',createRoapmap);
router.get('/:id',getRoadmap);
router.get('/',getAllRoadmap);

module.exports = router;
