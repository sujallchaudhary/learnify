const multer = require('multer');
const AWS = require('aws-sdk');
const path = require('path');
const uuid = require('uuid');

const cdnEndpoint = process.env.SPACE_CDN_ENDPOINT;
const spacesEndpoint = new AWS.Endpoint(process.env.SPACE_ENDPOINT);
const s3 = new AWS.S3({
    endpoint: spacesEndpoint,
    accessKeyId: process.env.SPACE_API_KEY,
    secretAccessKey: process.env.SPACE_API_SECRET
});

const storage = multer.memoryStorage();
const upload = multer({
    storage,
    limits: { fileSize: 10 * 1024 * 1024 }, // 10 MB limit
}).single('file');

const fileUpload = (req, res, next) => {
    upload(req, res, function (err) {
        if (err) {
            return res.status(400).json({ error: 'File upload error', details: err.message });
        }

        if (!req.file) {
            // No file uploaded, proceed to next middleware
            return next();
        }

        const fileKey = `files/${uuid.v4()}${path.extname(req.file.originalname)}`;
        const params = {
            Bucket: process.env.SPACE_NAME,
            Key: fileKey,
            Body: req.file.buffer,
            ACL: 'public-read',
            ContentType: req.file.mimetype
        };

        s3.upload(params, (error, data) => {
            if (error) {
                return res.status(500).json({ error: 'Failed to upload file', details: error.message });
            }

            // Preserve other fields in req.body and add file URL
            req.body.fileType=req.file.mimetype;
            req.body.fileName=req.file.originalname;
            req.body.file = `${cdnEndpoint}${fileKey}`;

            next();
        });
    });
};

module.exports = { fileUpload };