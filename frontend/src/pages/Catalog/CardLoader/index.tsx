import ContentLoader from 'react-content-loader'

const CardLoader = () => (
    <ContentLoader
        speed={2}
        width={400}
        height={460}
        viewBox = '0 0 400 400'
        backgroundColor='#f3f3f3'
        foregroundColor='#ecebeb'
    >
        <rect x='7' y='18' rx='2' ry='2' width='300' height='300' />
    </ContentLoader>
)

export default CardLoader;