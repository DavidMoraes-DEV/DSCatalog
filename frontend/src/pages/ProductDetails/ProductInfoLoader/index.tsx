import ContentLoader from 'react-content-loader';
import './styles.css';

const ProductInfoLoarder = () => (
  <ContentLoader
    className="loader-card-content"
    viewBox="0 0 100% 280"
    height="100%"
    width="100%"
  >
    <rect x="0" y="0" rx="10" ry="10" width="100%" height="100%" />
  </ContentLoader>
);

export default ProductInfoLoarder;
